package com.farm.config.routes;

<<<<<<< Updated upstream
=======
import com.farm.answer.controller.AnswerController;
import com.farm.crop.control.CropController;
>>>>>>> Stashed changes
import com.farm.user.controller.UserController;
import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {
    @Override
    public void config() {
        add("user", UserController.class);
<<<<<<< Updated upstream
=======
        add("crop",CropController.class);
        add("answer",AnswerController.class);
>>>>>>> Stashed changes
    }

}
