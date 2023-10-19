package SKRUM.TicketGuru.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import SKRUM.TicketGuru.auth.JwtUtil;
import SKRUM.TicketGuru.domain.User;
import SKRUM.TicketGuru.domain.exceptions.BadCredentialsCustomException;
import SKRUM.TicketGuru.domain.request.LoginReq;
import SKRUM.TicketGuru.domain.response.LoginRes;

@Controller
@RequestMapping("/api/auth")
public class RestAuthController {

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    public RestAuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUser(), loginReq.getPassword()));
            String username = authentication.getName();
            User user = new User(username, "");
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(username, token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsCustomException("Invalid username or password");
        }
    }
}