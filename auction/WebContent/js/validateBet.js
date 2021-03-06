const FORM_ID = "auction_place_bet";
const BET_ID = "placeBet";

const INVALID_BET_MESSAGE_ID = "auction_bet_invalid_message";

function validate(){
    if (!isNumeric(document.getElementById(BET_ID).value)){
    	event.preventDefault();
        document.getElementById(INVALID_BET_MESSAGE_ID).style.display = 'block';
    }
    else {
    	document.getElementById(FORM_ID).submit();
    }
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}