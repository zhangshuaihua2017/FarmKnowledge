package config.routes;

import com.jfinal.config.Routes;

import admin.controller.AdminController;

public class AdminRoutes extends Routes {
    @Override
    public void config() {
        add("admin",AdminController.class);
    }
}
