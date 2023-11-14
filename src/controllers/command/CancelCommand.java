package controllers.command;

import controllers.BaseController;

import java.awt.event.ActionEvent;

public class CancelCommand<T>  implements ICommand{

    private BaseController<T> controller;

    public CancelCommand(BaseController<T> controller){
        this.controller = controller;
    }

    @Override
    public void execute(ActionEvent e) {
        this.controller.onClickCancel(e);
    }
}