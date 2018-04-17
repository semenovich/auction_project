const FORM_ID = "create_auction";
const MIN_BET_ID = "auction_minimum_price";

const INVALID_BET_MESSAGE_ID = "auction_bet_invalid_message";

function validate(){
    if (!isNumeric(document.getElementById(MIN_BET_ID).value)){
    	event.preventDefault();
        document.getElementById(INVALID_BET_MESSAGE_ID).style.display = inline;
    }
    else {
    	document.getElementById(create_auction).submit();
    }
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}