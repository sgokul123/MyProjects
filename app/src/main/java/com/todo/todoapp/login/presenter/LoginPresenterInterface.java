package com.todo.todoapp.login.presenter;

/**
 * Created by bridgeit on 14/3/17.
 */

public interface LoginPresenterInterface {
        public  void getLogin(String email, String password);
        public  void getLoginAuthentication(Boolean flag);

        public  void showProgress();
        public  void closeProgress();

}
