package by.bsu.auction.controller.command;

public enum ServletList {
	LOGIN,
	LOGOUT,
	REGISTER,
	GET_USERS_LIST,
	GET_USERS_BY_SEARCHING,
	GET_USER_INFO,
	GET_USER_LOTS,
	GET_USER_AUCTION_PARTICIPATIONS,
	EDIT_USER_INFO,
	CREATE_LOT,
	OFFER_LOT,
	GET_LOT_INFO,
	GET_LOTS_LIST,
	GET_LOTS_BY_SEARCHING,
	GET_LOTS_BY_TYPE,
	DELETE_CONFIRMING_LOT,
	EDIT_CONFIRMING_LOT,
	CREATE_AUCTION_FROM_EXISTING_LOT,
	CREATE_AUCTION_WITH_LOT,
	GET_AUCTION_INFO,
	GET_AUCTIONS_BY_SEARCHING,
	GET_AUCTIONS_BY_LOT_TYPE,
	GET_AUCTIONS_LIST,
	BLOCK_USER,
	UNBLOCK_USER,
	BLOCK_LOT,
	UNBLOCK_LOT,
	PLACE_BET,
	PAY_FOR_LOT;
}
