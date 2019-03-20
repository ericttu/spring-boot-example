package dev.erictu.demoajax.controller;

import dev.erictu.demoajax.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ajax
 *
 * @author eric.t.tu
 * @date 2019/3/20 13:22
 */
@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @GetMapping("/get")
    public Map<String, String> get(@RequestParam String id) {
        Map<String, String> res = new HashMap<>(16);
        res.put("id", id);
        res.put("name", "sssxxc");
        return res;
    }

    @PostMapping("/post")
    public Map<String, String> post(@RequestBody User user) {
        Map<String, String> res = new HashMap<>(16);
        res.put("username", user.getUsername());
        res.put("password", user.getPassword());
        return res;
    }
}
