package by.tc.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.lot_operation.LotOperationService;
import by.tc.auction.service.user_operation.ProfileService;

public class ImageUploader extends HttpServlet {
	private static final long serialVersionUID = -1004377321372393848L;

	private static final Logger logger = Logger.getLogger(ImageUploader.class);
	
	private static final String FILE_PATH = "/home/semenovich/eclipse/JavaEE/auction/" + 
												"WebContent/images/";
	private static final String IMAGE_PATH = "images/";
	
	private static final String LOT_PARAMETER = "lot";
	private static final String USER_PARAMETER = "user";
	
	private static final int COMMAND = 0;
    private static final int IMAGE = 1;
    private static final int LOT_ID = 2;
    private static final int USER_LOGIN = 2;
    
    private static final String USER_SUCCESSFUL_PAGE = "FrontController?command=GET_USER_INFO&userLogin=";
    private static final String LOT_SUCCESSFUL_PAGE = "FrontController?command=GET_LOT_INFO&lotId=";
    
    private static final String ERROR_PAGE = "error.jsp";
	
    private ProfileService profileService;
    private LotOperationService lotOperationService;
    
    public ImageUploader() {
    	super();
    	ServiceFactory factory = ServiceFactory.getInstance();
    	profileService = factory.getProfileService();
    	lotOperationService = factory.getLotOpeationService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(ERROR_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            List<FileItem> items = upload.parseRequest(request);
	            String message = items.get(COMMAND).getString();
	            FileItem fi = (FileItem) items.get(IMAGE);
	            if (!fi.isFormField()) {
	            	String imagePath = null;
	            	if (!fi.getName().isEmpty()) {
	            		imagePath = IMAGE_PATH + fi.getName();
			            File image = new File(FILE_PATH + fi.getName());
			            fi.write(image);
	            	}
		            switch (message) {
		                case USER_PARAMETER:
		                	String userLogin = items.get(USER_LOGIN).getString();
		                	if (imagePath == null || profileService.uploadUserImage(userLogin, imagePath)) {
		                		response.sendRedirect(USER_SUCCESSFUL_PAGE + userLogin);
		                	}
		                	else {
		                		response.sendRedirect(ERROR_PAGE);
		                	}
		                    break;
		                case LOT_PARAMETER:
		                    Integer lotId = Integer.valueOf(items.get(LOT_ID).getString());
		                    if (imagePath == null || lotOperationService.uploadLotImage(lotId, imagePath)) {
		                		response.sendRedirect(LOT_SUCCESSFUL_PAGE + lotId);
		                	}
		                	else {
		                		response.sendRedirect(ERROR_PAGE);
		                	}
		                    break;
		            }
	            }
	        }
		} catch (FileUploadException e) {
			logger.error("FileUploadException in ImageUploader", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (Exception e) {
			logger.error("Error in ImageUploader", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}


}
