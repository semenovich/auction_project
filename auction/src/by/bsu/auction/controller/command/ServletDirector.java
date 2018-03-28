package by.bsu.auction.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.bsu.auction.controller.command.realization.admin_operation.BlockLot;
import by.bsu.auction.controller.command.realization.admin_operation.BlockUser;
import by.bsu.auction.controller.command.realization.admin_operation.UnblockLot;
import by.bsu.auction.controller.command.realization.admin_operation.UnblockUser;
import by.bsu.auction.controller.command.realization.auction_operation.CreateAuctionFromExistingLot;
import by.bsu.auction.controller.command.realization.auction_operation.CreateAuctionWithLot;
import by.bsu.auction.controller.command.realization.auction_operation.GetAuctionInfo;
import by.bsu.auction.controller.command.realization.auction_operation.GetAuctionsBySearching;
import by.bsu.auction.controller.command.realization.auction_operation.GetAuctionsList;
import by.bsu.auction.controller.command.realization.authentication.Login;
import by.bsu.auction.controller.command.realization.authentication.Logout;
import by.bsu.auction.controller.command.realization.authentication.Register;
import by.bsu.auction.controller.command.realization.lot_operation.CreateLot;
import by.bsu.auction.controller.command.realization.lot_operation.DeleteConfirmingLot;
import by.bsu.auction.controller.command.realization.lot_operation.EditConfirmingLot;
import by.bsu.auction.controller.command.realization.lot_operation.GetLotInfo;
import by.bsu.auction.controller.command.realization.lot_operation.GetLotsBySearching;
import by.bsu.auction.controller.command.realization.lot_operation.GetLotsList;
import by.bsu.auction.controller.command.realization.lot_operation.OfferLot;
import by.bsu.auction.controller.command.realization.user_operation.EditUserInfo;
import by.bsu.auction.controller.command.realization.user_operation.GetUserAuctionParticipations;
import by.bsu.auction.controller.command.realization.user_operation.GetUserInfo;
import by.bsu.auction.controller.command.realization.user_operation.GetUserLots;
import by.bsu.auction.controller.command.realization.user_operation.GetUsersBySearching;
import by.bsu.auction.controller.command.realization.user_operation.GetUsersList;
import by.bsu.auction.controller.command.realization.user_operation.PayForLot;
import by.bsu.auction.controller.command.realization.user_operation.PlaceBet;

public final class ServletDirector {

	private static ServletDirector instance = new ServletDirector();
	
	private Map <ServletList,ServletCommand> commands = new HashMap<>(); 
	
	private ServletDirector() {
		commands.put(ServletList.LOGIN, new Login());
		commands.put(ServletList.LOGOUT, new Logout());
		commands.put(ServletList.REGISTER, new Register());
		commands.put(ServletList.GET_USER_INFO, new GetUserInfo());
		commands.put(ServletList.GET_USER_LOTS, new GetUserLots());
		commands.put(ServletList.GET_USER_AUCTION_PARTICIPATIONS, new GetUserAuctionParticipations());
		commands.put(ServletList.EDIT_USER_INFO, new EditUserInfo());
		commands.put(ServletList.CREATE_LOT, new CreateLot());
		commands.put(ServletList.OFFER_LOT, new OfferLot());
		commands.put(ServletList.GET_LOT_INFO, new GetLotInfo());
		commands.put(ServletList.GET_LOTS_LIST, new GetLotsList());
		commands.put(ServletList.GET_LOTS_BY_SEARCHING, new GetLotsBySearching());
		commands.put(ServletList.DELETE_CONFIRMING_LOT, new DeleteConfirmingLot());
		commands.put(ServletList.EDIT_CONFIRMING_LOT, new EditConfirmingLot());
		commands.put(ServletList.GET_USERS_LIST, new GetUsersList());
		commands.put(ServletList.GET_USERS_BY_SEARCHING, new GetUsersBySearching());
		commands.put(ServletList.CREATE_AUCTION_FROM_EXISTING_LOT, new CreateAuctionFromExistingLot());
		commands.put(ServletList.CREATE_AUCTION_WITH_LOT, new CreateAuctionWithLot());
		commands.put(ServletList.GET_AUCTION_INFO, new GetAuctionInfo());
		commands.put(ServletList.GET_AUCTIONS_BY_SEARCHING, new GetAuctionsBySearching());
		commands.put(ServletList.GET_AUCTIONS_LIST, new GetAuctionsList());
		commands.put(ServletList.BLOCK_USER, new BlockUser());
		commands.put(ServletList.UNBLOCK_USER, new UnblockUser());
		commands.put(ServletList.BLOCK_LOT, new BlockLot());
		commands.put(ServletList.UNBLOCK_LOT, new UnblockLot());
		commands.put(ServletList.PLACE_BET, new PlaceBet());
		commands.put(ServletList.PAY_FOR_LOT, new PayForLot());
	}

	public static ServletDirector getInstance(){
		return instance;
	}
	
	public ServletCommand getCommand(ServletList command) {
		return commands.get(command);
	}
}
