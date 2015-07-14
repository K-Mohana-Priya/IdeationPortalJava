package controller;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import Beans.*;
import dao.*;
/**
 * Servlet implementation class LoginController
 */
public class SubmitIdea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitIdea() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("fileName");
		 if(fileName == null || fileName.equals("")){
		   throw new ServletException("File Name can't be null or empty");
		 }
		 File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
		if(!file.exists()){
		 throw new ServletException("File doesn't exists on server.");
		}
		 System.out.println("File location on server::"+file.getAbsolutePath());
		 ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		 String mimeType = ctx.getMimeType(file.getAbsolutePath());
		 response.setContentType(mimeType != null? mimeType:"application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		 ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		 int read=0;
		while((read = fis.read(bufferData))!= -1){
		 os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		userbean user=(userbean)session.getAttribute("user");
	       String ideabody="";
	        String des=null;
	        String target=null;
	        String impact=null;
	        String title="",submit_for="";
	         InputStream fileContent=null;
	        OutputStream outputfile=null;
	        String [] filessaved=new String[50];
	        int c=0;
	        try{
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	        	if (item.isFormField()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                // ... (do your job here)
                if(fieldName.equals("title")){
                	title=fieldValue;
                }
                if(fieldName.equals("description")){
                	des=fieldValue;
                }
                if(fieldName.equals("target")){
                	target=fieldValue;
                }
                if(fieldName.equals("impact")){
                	impact=fieldValue;
                }
                if(fieldName.equals("submit_for")){
                	submit_for=fieldValue;
                }
                
            } else {
	                // Process form file field (input type="file").
            	    String fileName = item.getName(); 
            	   // String spath="uploads";
                    File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
                    filessaved[c++]=fileName;
                    item.write(file);
	            }
	        }
	        
	        if(!des.equals(""))
	        	ideabody+="Description: "+des+" ";
	        if(!target.equals(""))
	        	ideabody+="target: "+target+" ";
	        if(!impact.equals(""))
	        	ideabody+="Impact:  "+impact+" ";
	        System.out.println("Idea Body:"+ideabody);
	        notificationsdao nda=new notificationsdao();
			String message = "A new idea titled "+title+" has been submitted by "+user.getUser_name();
			 System.out.println("Notification:"+message);
	       IdeaDAO_1 idao = new IdeaDAO_1();
	       System.out.println("Hello before "+submit_for);
	       
	       if(submit_for.equals("submit_for_review")){
	    	   System.out.println("Hello here");
	    	   idao.insertIdeaForReview(title,ideabody,filessaved,c,user.getUser_id(),message);
	       }
	       else{
	    	   String grp_name1=null,grp_name2=null;
	    	   System.out.println("Hello manager_id "+user.getManager_id_1());
	    	   if(!user.getManager_id_1().equals(null))
	    	   grp_name1=new user_group_assodao().getUserGroupbyUId(user.getManager_id_1());
	    	   if(!user.getManager_id_1().equals(null))
	    	   grp_name2=new user_group_assodao().getUserGroupbyUId(user.getManager_id_2());
	    	   if(grp_name1.equals(submit_for)){
		    	   idao.insertIdeaForApproval(title,ideabody,filessaved,c,user.getUser_id(),user.getManager_id_1(),
		    			   submit_for); 
		    	   nda.insertNotification(user.getManager_id_1(), message);
		    	   }
		    	   else if(grp_name2.equals(submit_for)){
		    	   idao.insertIdeaForApproval(title,ideabody,filessaved,c,user.getUser_id(),user.getManager_id_2(),
			    			   submit_for);  
		    	   nda.insertNotification(user.getManager_id_2(), message);
		    	   }
	       }
		System.out.println("IDEA inserted");
				RequestDispatcher rd = request.getRequestDispatcher("success.html");
				rd.forward(request, response); 
		}catch (FileUploadException e) {
    e.printStackTrace();
} catch (Exception e) {
    e.printStackTrace();
}
}
}