package com.ust.websecurityxam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.websecurityxam.entity.Issue;
import com.ust.websecurityxam.entity.User;
import com.ust.websecurityxam.exception.UserNotFoundException;
import com.ust.websecurityxam.repository.IssueRepository;
import com.ust.websecurityxam.repository.UserRepository;

@RestController
@RequestMapping("library")
public class LibraryController {
    @Autowired
    private UserRepository ur;
    @Autowired
    private IssueRepository ir;

    @PostMapping("/issue")
    public ResponseEntity<Object> issueBook(@RequestBody Issue issue)
    {
        Optional<User> user = ur.findById(issue.getUser().getId());
        if(user.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        if (user.get().getSubscribed()==false)
        {
            throw new UserNotFoundException("User is not founded");
        }
        return ResponseEntity.ok().body(ur.save(user.get()));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        return ResponseEntity.ok().body(ur.save(user));
    }

    @GetMapping("renew-user-subscription/{id}")
    public ResponseEntity<User> renewUserSubcription(@PathVariable Long id){
        Optional<User> temp = ur.findById(id);
        if(temp.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        else{
            User user = temp.get();
            user.setSubscribed(true);
            return ResponseEntity.ok().body(ur.save(user));

        }
    }
    
    @PutMapping("/user1")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        User updated=null;
        Optional<User> u = ur.findById(user.getId());
        if(u.isPresent())
        {
            updated=u.get();
            updated.setId(user.getId());
            // updated.setIssues(user.getIssues());
            updated.setName(user.getName());
            updated.setSubscribed(user.getSubscribed());
            ur.save(updated);
            return ResponseEntity.ok().body(updated);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/issue-book")
    public ResponseEntity<Issue> updateIssue(@RequestBody Issue issue){
        Issue updated=null;
        Optional<Issue> i = ir.findById(issue.getId());
        if(i.isPresent())
        {
            updated=i.get();
            updated.setId(issue.getId());
            updated.setFine(issue.getFine());
            updated.setPeriod(issue.getPeriod());
            updated.setIssueDate(issue.getIssueDate());
            updated.setReturnDate(issue.getReturnDate());
            ir.save(updated);
            return ResponseEntity.ok().body(updated);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/usr/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        Optional<User> user = ur.findById(id);
        if(user.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }else{
            ur.delete(user.get());
        }
        return ResponseEntity.ok().body("Deleted..");
    }

    @DeleteMapping("/issue/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable long id){
        Optional<Issue> ise = ir.findById(id);
        if(ise.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }else{
            ir.delete(ise.get());
        }
        return ResponseEntity.ok().body("Deleted..");
    }





    
}
