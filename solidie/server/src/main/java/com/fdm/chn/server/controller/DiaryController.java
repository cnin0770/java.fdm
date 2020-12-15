package com.fdm.chn.server.controller;

import com.fdm.chn.server.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/diaries")
@Log4j2
public class DiaryController {
    private final UserRepo userRepo;
    private final DiaryRepo diaryRepo;

    @Autowired
    public DiaryController(UserRepo userRepo, DiaryRepo diaryRepo) {
        this.userRepo = userRepo;
        this.diaryRepo = diaryRepo;
    }

    @GetMapping
    public Collection<Diary> findAll(Principal principal) {
        return diaryRepo.findAllByUserId(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable long id) {
        Optional<Diary> item = diaryRepo.findById(id);
        return item.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Diary> create(@Valid @RequestBody Diary item, @AuthenticationPrincipal OAuth2User pincipal) throws URISyntaxException {
        log.info("creating diary entity: {}", item);
        Map<String, Object> details = pincipal.getAttributes();
        String userId = details.get("sub").toString();
        Optional<User> user = userRepo.findById(userId);
        item.setUser(user.orElse(new User(userId, details.get("name").toString(), details.get("email").toString())));
        Diary result = diaryRepo.save(item);
        return ResponseEntity.created(new URI("/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diary> update(@Valid @RequestBody Diary item) {
        log.info("updating diary entity: {}", item);
        Diary result = diaryRepo.save(item);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        log.info("removing diary entity: {}", id);
        diaryRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
