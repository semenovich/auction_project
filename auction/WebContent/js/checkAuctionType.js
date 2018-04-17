const AUCTION_TYPE_SELECTOR_ID = "auction_type_select";
const AUCTION_ONLINE_TYPE_END_TIME_SELECTOR = "online_type_end_time_select";

const ENGLISH = "ENGLISH";
const ONLINE = "ONLINE";

function checkType() {
    if (document.getElementById(AUCTION_TYPE_SELECTOR_ID).value == ONLINE){
        document.getElementById(AUCTION_ONLINE_TYPE_END_TIME_SELECTOR).style.display = 'block';
    }
    if (document.getElementById(AUCTION_TYPE_SELECTOR_ID).value == ENGLISH){
        document.getElementById(AUCTION_ONLINE_TYPE_END_TIME_SELECTOR).style.display = 'none';
    }
}