const FORM_CLASS = "user_edit_form";
const PHONE_ID = "userPhone";
const EMAIL_ID = "userEmail";

const MESSAGE_BLOCK_ID = "user_edit_info_invalid_message";

function validate(){

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
    document.getElementById(MESSAGE_BLOCK_ID).style.display = 'block';
    return;
}