package com.rethinkwebdesign.myteamer.controller;

import com.rethinkwebdesign.myteamer.model.Coach;
import com.rethinkwebdesign.myteamer.model.Team;
import com.rethinkwebdesign.myteamer.model.User;
import com.rethinkwebdesign.myteamer.repository.CoachRepository;
import com.rethinkwebdesign.myteamer.repository.TeamRepository;
import com.rethinkwebdesign.myteamer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

}
