package controllers.command;

import controllers.BaseController;

import java.awt.event.ActionEvent;

public class SaveCommand<T>  implements ICommand{

    private BaseController<T> controller;

    public SaveCommand(BaseController<T> controller){
        this.controller = controller;
    }

    @Override
    public void execute(ActionEvent e) {
        this.controller.onClickSave(e);
    }
}
