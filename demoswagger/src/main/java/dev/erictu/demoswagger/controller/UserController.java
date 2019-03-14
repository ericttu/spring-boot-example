package dev.erictu.demoswagger.controller;

import dev.erictu.demoswagger.domain.User;
import dev.erictu.demoswagger.security.JwtUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.*;

/**
 * userController
 *
 * @author eric.t.tu
 * @date 2019/3/14 10:37
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation("获取用户列表")
    @GetMapping("/")
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/")
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "123")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "123"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "123")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws ServletException {
        String name = user.getName();
        Long id = user.getId();
        if (!"admin".equals(name)) {
            throw new ServletException("no such user");
        }
        if (1 != id) {
            throw new ServletException("no such id");
        }

        return JwtUtil.getToken(name);
    }
}
