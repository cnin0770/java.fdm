package com.fdm.chn.server.controller;

import com.fdm.chn.server.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
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
@RequestMapping("/api/exercises")
@Log4j2
public class ExerciseController {
    private final UserRepo userRepo;
    private final DiaryRepo diaryRepo;
    private final ExerciseRepo exerciseRepo;

    @Autowired
    public ExerciseController(UserRepo userRepo, DiaryRepo diaryRepo, ExerciseRepo exerciseRepo) {
        this.userRepo = userRepo;
        this.diaryRepo = diaryRepo;
        this.exerciseRepo = exerciseRepo;
    }

    @GetMapping
    public Collection<Exercise> findAll(Principal principal) {
        return exerciseRepo.findAllByUserId(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable long id) {
        Optional<Exercise> item = exerciseRepo.findById(id);
        return item.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@Valid @RequestBody Exercise item, @AuthenticationPrincipal OAuth2User principal) throws URISyntaxException {
        log.info("creating exercise entity: {}", item);
        Map<String, Object> details = principal.getAttributes();
        String userId = details.get("sub").toString();

        Optional<User> user = userRepo.findById(userId);
        item.setUser(user.orElse(new User(userId, details.get("name").toString(), details.get("email").toString())));

        Exercise result = exerciseRepo.save(item);
        return ResponseEntity.created(new URI("/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(@Valid @RequestBody Exercise item) {
        log.info("updating exercise entity: {}", item);
        Exercise result = exerciseRepo.save(item);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        log.info("removing exercise entity: {}", id);
        exerciseRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
