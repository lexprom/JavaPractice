package com.park.alex.Controller;

import com.park.alex.DTO.OperationDTO;
import com.park.alex.DTO.RollbackOperationDTO;
import com.park.alex.DTO.UserDTO;
import com.park.alex.Model.BankAccount;
import com.park.alex.Model.BankOperation;
import com.park.alex.Model.UserEntity;
import com.park.alex.Repository.BankAccountRepository;
import com.park.alex.Repository.BankOperationRepository;
import com.park.alex.Repository.UserRepository;
import com.park.alex.Services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Controller
public class Controller {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankOperationRepository bankOperationRepository;
    private final OperationService operationService;

    @Autowired
    public Controller(UserRepository userRepository, BankAccountRepository bankAccountRepository, BankOperationRepository bankOperationRepository, OperationService operationService) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.bankOperationRepository = bankOperationRepository;
        this.operationService = operationService;
    }

    private String userInfo(Model model, UserEntity user, String action, String returnUrl, Boolean isShow) {
        model.addAttribute("user", user);
        model.addAttribute("userId", user.getId());
        model.addAttribute("action", action);
        model.addAttribute("returnUrl", returnUrl);
        model.addAttribute("show", isShow);
        return "user-info";
    }

    @GetMapping("/registration")
    public String registrationGet(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute UserEntity user) {
        user = userRepository.save(user);
        return "redirect:/home/" + user.getId();
    }

    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute UserDTO userDTO) {
        Optional<UserEntity> user = userRepository.findByLogin(userDTO.getLogin());
        if (user.isPresent() && user.get().getPassword().equals(userDTO.getPassword())) {
            return "redirect:/home/" + user.get().getId();
        }
        return "redirect:/login";
    }

    @GetMapping("/home/{userId}")
    public String homeGet(Model model, @PathVariable("userId") String userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("admin", userRepository.findById(Long.valueOf(userId)).get().isAdmin());
        return "home";
    }

    @GetMapping("/home-admin")
    public String homeAdminGet() {
        return "home-admin";
    }

    @GetMapping("/user/{userId}/bills")
    public String accountsGet(Model model, @PathVariable String userId) {
        List<BankAccount> accounts;
        Boolean isAdmin = true;

        if ("all".equals(userId)) {
            accounts = new ArrayList<>();
            bankAccountRepository.findAll().forEach(accounts::add);
        } else {
            UserEntity userEntity = userRepository.findById(Long.valueOf(userId)).get();
            accounts = userEntity.getAccounts();
            isAdmin = false;
        }

        model.addAttribute("userId", userId);
        model.addAttribute("accounts", accounts);
        model.addAttribute("hidden", isAdmin);
        return "bills";
    }

    @GetMapping("/user/{userId}/add-account")
    public String addAccountGet(Model model, @PathVariable String userId) {
        model.addAttribute("user", userRepository.findById(Long.valueOf(userId)).get());
        model.addAttribute("account", new BankAccount());
        return "add-account";
    }

    @PostMapping("/user/{userId}/add-account")
    public String addAccountPost(@ModelAttribute BankAccount account, @PathVariable String userId) {
        UserEntity userEntity = userRepository.findById(Long.valueOf(userId)).get();
        account.setOwner(userEntity);
        if (account.getAmount() == null) {
            account.setAmount(0L);
        }
        bankAccountRepository.save(account);
        return "redirect:/user/" + userEntity.getId() + "/bills";
    }

    @GetMapping("/user/{userId}/operations")
    public String operationsGet(Model model, @PathVariable String userId) {
        List<BankOperation> operations;
        Boolean isAdmin = true;

        if ("all".equals(userId)) {
            operations = new ArrayList<>();
            bankOperationRepository.findAll().forEach(operations::add);
        } else {
            UserEntity userEntity = userRepository.findById(Long.valueOf(userId)).get();
            operations = StreamSupport
                    .stream(bankOperationRepository.findAll().spliterator(), false)
                    .filter(operation -> userEntity.getAccounts().contains(operation.getSender())
                            || userEntity.getAccounts().contains(operation.getReceiver()))
                    .collect(Collectors.toList());
            isAdmin = false;
        }

        model.addAttribute("userId", userId);
        model.addAttribute("operations", operations);
        model.addAttribute("admin", isAdmin);
        model.addAttribute("rollbackOperation", new RollbackOperationDTO());
        return "operations";
    }

    @PostMapping("/user/{userId}/operations")
    public String rollbackOperationPost(@ModelAttribute RollbackOperationDTO rollbackOperationDTO, @PathVariable String userId) {
        BankOperation operation = bankOperationRepository.findById(rollbackOperationDTO.getOperationId()).get();
        operationService.rollback(operation);
        return "redirect:/user/all/operations";
    }

    @GetMapping("/user/{userId}/add-operation")
    public String addOperationGet(Model model, @PathVariable String userId) {
        model.addAttribute("user", userRepository.findById(Long.valueOf(userId)).get());
        model.addAttribute("operation", new OperationDTO());
        model.addAttribute("accounts", bankAccountRepository.findAll());
        return "add-operation";
    }

    @PostMapping("/user/{userId}/add-operation")
    public String addOperationPost(@ModelAttribute OperationDTO operationDTO, @PathVariable String userId) {
        UserEntity userEntity = userRepository.findById(Long.valueOf(userId)).get();
        BankOperation operation = new BankOperation();
        operation.setSender(bankAccountRepository.findById(operationDTO.getSenderId()).get());
        operation.setReceiver(bankAccountRepository.findById(operationDTO.getReceiverId()).get());
        operation.setAmount(operationDTO.getAmount());
        if (operation.getAmount() == null) {
            operation.setAmount(0L);
        }
        operationService.commit(operation);
        return "redirect:/user/" + userEntity.getId() + "/operations";
    }

    @GetMapping("/users")
    public String usersGet(Model model) {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        model.addAttribute("users", users);
        model.addAttribute("userDto", new UserDTO());
        return "users";
    }

    @PostMapping("/users")
    public String usersPost(@ModelAttribute UserDTO userDTO, @RequestParam(value="action") String action) {
        if (userDTO.getUserId() == null) {
            return "redirect:/users";
        }
        System.out.println(action);

        switch (action) {
            case "remove":
                userRepository.deleteById(userDTO.getUserId());
                return "redirect:/users";
            case "edit":
                return "redirect:/edit-user/" + userDTO.getUserId() + "/users";
            case "show": default:
                return "redirect:/show-user/" + userDTO.getUserId() + "/users";
        }
    }

    @GetMapping("/add-user")
    public String addUserGet(Model model) {
        return userInfo(model, new UserEntity(), "add-user", "users", false);
    }

    @PostMapping("/add-user/{userId}/{returnUrl}")
    public String addUserPost(@ModelAttribute UserEntity user, @PathVariable("userId") String userId, @PathVariable("returnUrl") String returnUrl) {
        userRepository.save(user);
        return "redirect:/" + returnUrl;
    }

    @GetMapping("/edit-user/{userId}/{returnUrl}")
    public String editUserGet(Model model, @PathVariable("userId") String userId, @PathVariable("returnUrl") String returnUrl) {
        return userInfo(model, userRepository.findById(Long.valueOf(userId)).get(), "edit-user", returnUrl, false);
    }

    @PostMapping("/edit-user/{userId}/{returnUrl}")
    public String editUserPost(@ModelAttribute UserEntity user, @PathVariable("userId") String userId, @PathVariable("returnUrl") String returnUrl) {
        user.setId(Long.valueOf(userId));
        userRepository.save(user);
        if (returnUrl.equals("home")) {
            returnUrl = returnUrl.concat("/" + userId);
        }
        return "redirect:/" + returnUrl;
    }

    @GetMapping("/show-user/{userId}/{returnUrl}")
    public String showUserGet(Model model, @PathVariable("userId") String userId, @PathVariable("returnUrl") String returnUrl) {
        return userInfo(model, userRepository.findById(Long.valueOf(userId)).get(), "show-user", returnUrl, true);
    }

    @PostMapping("/show-user/{userId}/{returnUrl}")
    public String showUserPost(@ModelAttribute UserEntity user, @PathVariable("userId") String userId, @PathVariable("returnUrl") String returnUrl) {
        if (returnUrl.equals("home")) {
            returnUrl = returnUrl.concat("/" + userId);
        }
        return "redirect:/" + returnUrl;
    }
}