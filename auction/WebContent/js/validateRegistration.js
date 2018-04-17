const FORM_CLASS = "register_form";
const LOGIN_ID = "userLogin";
const PHONE_ID = "userPhone";
const EMAIL_ID = "userEmail";

const MESSAGE_BLOCK_ID = "register_data_invalid_text";
const USER_EXISTS_MESSAGE_ID = "register_user_existance_text";

function validate(){
    var loginRegex = /^\w+$/;
    if (!loginRegex.test(document.getElementById(LOGIN_ID).value)){
        invalidInput();
        return false;
    }

    var emailRegex = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    if (!emailRegex.test(document.getElementById(EMAIL_ID).value)){
        invalidInput();
        return false;
    }

    var phoneRegex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
    if (!phoneRegex.test(document.getElementById(PHONE_ID).value)){
        invalidInput();
        return false;
    }
    document.getElementsByClassName(FORM_CLASS)[0].submit();
}

function invalidInput(){
    event.preventDefault();
    if (document.getElementById(USER_EXISTS_MESSAGE_ID) != null) {
        document.getElementById(USER_EXISTS_MESSAGE_ID).style.display = 'none';
    }
    document.getElementById(MESSAGE_BLOCK_ID).style.display = 'block';
    return;
}