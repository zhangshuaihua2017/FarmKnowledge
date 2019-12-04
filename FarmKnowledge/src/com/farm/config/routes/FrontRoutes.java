package com.farm.config.routes;

import com.farm.answer.controller.AnswerController;
import com.farm.crop.control.CropController;
import com.farm.user.controller.UserController;
import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {
    @Override
    public void config() {
        add("user", UserController.class);
        add("crop",CropController.class);
        add("answer",AnswerController.class);
    }

}
