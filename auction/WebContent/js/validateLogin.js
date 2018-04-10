const FORM_CLASS = "login_form";

const LOGIN_ID = "userLogin";

const MESSAGE_BLOCK_ID = "login_data_invalid_text";
const USER_DOESNT_EXIST_MESSAGE_ID = "login_user_existance_text";

function validate(){
    var loginRegex = /^\w+$/;
    if (!loginRegex.test(document.getElementById(LOGIN_ID).value)){
        invalidInput();
        return false;
    }
    document.getElementsByClassName(FORM_CLASS)[0].submit();
}

function invalidInput(){
    event.preventDefault();
    if (document.getElementById(USER_DOESNT_EXIST_MESSAGE_ID) != null) {
        document.getElementById(USER_DOESNT_EXIST_MESSAGE_ID).style.visibility = 'invisible';
    }
    document.getElementById(MESSAGE_BLOCK_ID).style.visibility = 'visible';
    return;
}