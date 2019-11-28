package config.routes;

import com.jfinal.config.Routes;

import user.controller.UserController;

public class FrontRoutes extends Routes {
    @Override
    public void config() {
        add("user", UserController.class);
    }
}
