package controllers.command;

import controllers.BaseController;

import java.awt.event.ActionEvent;

public class NewCommand<T> implements ICommand {
    private BaseController<T> controller;

    public NewCommand(BaseController<T> controller){
        this.controller = controller;
    }

    @Override
    public void execute(ActionEvent e) {
        this.controller.onClickNew(e);
    }
}
