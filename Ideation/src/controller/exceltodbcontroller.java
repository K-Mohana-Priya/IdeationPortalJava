package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;





import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utility.FileUpload;
import Beans.userbean;
import dao.user_roledao;
import dao.userdao;

/**
 * Servlet implementation class exceltodbcontroller
 */
@WebServlet("/exceltodbcontroller")
public class exceltodbcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static XSSFRow row;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public exceltodbcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String filepath=null;
        String id=null,name=null,email=null,mobile=null,doj=null,password=null,man1=null,man2=null,check="success";
        int level=0,layer=0;
       
       /* filepath=request.getParameter("file");
        */
        String message="";
       // out.print("<html><body><h3>"+filepath+"</h3></body></html>");
        boolean isMultiPart=ServletFileUpload.isMultipartContent(request);
        HttpSession session=request.getSession();
        if(isMultiPart)
        {
            ServletFileUpload upload=new ServletFileUpload();
            
           try
           {
                FileItemIterator itr=upload.getItemIterator(request);
              while(itr.hasNext())
                {
                    FileItemStream item=itr.next();
                    if(!item.isFormField())
                    {
                       
                        String path=getServletContext().getInitParameter("file-upload");
                        request.setAttribute("path", path);
                                              
                        if(FileUpload.processFile(path, item))
                        {
                        	filepath="C:\\Users\\shagayaraj\\Documents\\Tina\\webtechlab\\Ideation\\WebContent"+"\\"+item.getName();
                                            
                        String[] ext=filepath.split("\\.");
                         String ext1=ext[0];
                         String ext2=ext[1];
                         if(ext2.equals("xlsx"))
                         {
                             int rc=0;
                         FileInputStream fis=new FileInputStream(new File(filepath));
                
                 XSSFWorkbook wb=new XSSFWorkbook(fis);
                 XSSFSheet spreadsheet=wb.getSheetAt(0);
               
             //System.out.println(list);
                  Iterator < Row > rowIterator = spreadsheet.iterator();
                   while (rowIterator.hasNext()) 
                   {
                 	  check="success";
                      row = (XSSFRow) rowIterator.next();
                      rc++;
                      Iterator < Cell > cellIterator = row.cellIterator();
                      while ( cellIterator.hasNext()) 
                      {
                         Cell cell = cellIterator.next();
                         //out.print("<h3>"+cell.getCellType()+"</h3><br>");
                         switch (cell.getCellType()) 
                         {
                            case Cell.CELL_TYPE_NUMERIC:
                                if(cell.getColumnIndex()==4)
                                {
                                DataFormatter df=new DataFormatter();
                                if((df.formatCellValue(cell)).matches("^([0-9]{4})-([0-9]{2})-([0-9]{2})$"))
                                      doj=df.formatCellValue(cell);
                               else
                               { message+="<p>Invalid Date format at row:"+rc+" and Column:5</p>";
                                 check="error";
                               }
                                //out.print( "<h3>"+cvd+"</h3><br>");
                                }
                                else if(cell.getColumnIndex()==7)
                                {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                            if((cell.getStringCellValue()).matches("[0-9]{10}"))
                                 mobile=cell.getStringCellValue();
                            else
                            {
                         	   message+="<p>Invalid Mobile Number format at row:"+rc+" and Column:8</p>";
                         	   check="error";
                            }
                                }
                                else if(cell.getColumnIndex()==5)
                                {
                             	   cell.setCellType(Cell.CELL_TYPE_STRING);
                                    if((cell.getStringCellValue()).matches("[0-9]+"))
                                         level=Integer.parseInt(cell.getStringCellValue());
                                    else
                                    {
                                 	   message+="<p>Invalid Level at row:"+rc+" and Column:6</p>";
                                 	   check="error";
                                    }
                                }
                                else if(cell.getColumnIndex()==6)
                                {
                             	   cell.setCellType(Cell.CELL_TYPE_STRING);
                                    if((cell.getStringCellValue()).matches("[0-9]+"))
                                         layer=Integer.parseInt(cell.getStringCellValue());
                                    else
                                    {
                                 	   message+="<p>Invalid Layer at row:"+rc+" and Column:7</p>";
                                 	   check="error";
                                    }
                                }
                            break;
                            case Cell.CELL_TYPE_STRING:
                         	if(cell.getColumnIndex()==0)
                         	{
                         		id=cell.getStringCellValue();
                     			String test;
                     			if(id.isEmpty())
                     				test="invalid";
                     			else
                     			{
                     				userdao userd=new userdao();
                     				userbean user=userd.getUserbyId(id);
                     				if(user==null)
                     				{
                     					test="valid";
                     				}
                     				else
                     					test="invalid";
                     			}
                     			if(test.equals("invalid"))
                     			{
                     				message+="<p>User Id is either null or already exists at row:"+rc+" and Column:1</p>";
                              	   check="error";
                     			}
                         	}
                         	if(cell.getColumnIndex()==8)
                         	{
                         		man1=cell.getStringCellValue();
                         		String test;
                     			if(man1.isEmpty())
                     				test="valid";
                     			else
                     			{
                     				userdao userd=new userdao();
                     				userbean user=userd.getUserbyId(man1);
                     				if(user==null)
                     				{
                     					test="invalid";
                     				}
                     				else
                     					test="valid";
                     			}
                     			if(test.equals("invalid"))
                     			{
                     				message+="<p>Invalid Manager Id at row:"+rc+" and Column:9</p>";
                              	   check="error";
                     			}
                         	}
                         	if(cell.getColumnIndex()==9)
                         	{
                         		man2=cell.getStringCellValue();
                         		String test;
                     			if(man2.isEmpty())
                     				test="valid";
                     			else
                     			{
                     				userdao userd=new userdao();
                     				userbean user=userd.getUserbyId(man2);
                     				if(user==null)
                     				{
                     					test="invalid";
                     				}
                     				else
                     					test="valid";
                     			}
                     			if(test.equals("invalid"))
                     			{
                     				message+="<p>Invalid Manager Id at row:"+rc+" and Column:10</p>";
                              	   check="error";
                     			}
                         	}
                           
                            if(cell.getColumnIndex()==1)
                            {
                                 if((cell.getStringCellValue()).matches("^[a-zA-Z\\s]+"))
                                 name=cell.getStringCellValue();
                            else
                            {
                         	   message+="<p>Invalid name format at row:"+rc+" and Column:2</p>";
                         	   check="error";
                            }
                               
                            }
                            
                            if(cell.getColumnIndex()==2)
                                 {
                                 if((cell.getStringCellValue()).matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$"))
                                 email=cell.getStringCellValue();
                            else
                            {
                         	   message+="<p>Invalid email format at row:"+rc+" and Column:3</p>";
                         	   check="error";
                            }
                            }
                            
                            if(cell.getColumnIndex()==3)
                                password=cell.getStringCellValue();
                            
                            break;
                                
                         }
                      }
                      if(check.equals("success")){
                      java.sql.Date date_joined = java.sql.Date.valueOf(doj);
                      userdao userd=new userdao();
               	   userd.setUser(id, name, email, password, date_joined, level, layer, 0, mobile, man1, man2,false,true);
                      user_roledao user_roled=new user_roledao();
                      user_roled.setUserRolebyUId(id,"3");
                      }
                   }
                   fis.close();
                         }//if closed
                         else if(ext2.equals("xls"))
                         {
                             int rc=0;
                              File file=new File(filepath);
                 InputStream input = new BufferedInputStream(new FileInputStream(file));
                 
                 POIFSFileSystem fs = new POIFSFileSystem(input);
                 HSSFWorkbook wb = new HSSFWorkbook(fs);
                 HSSFSheet sheet = wb.getSheetAt(0);
              Iterator rows=sheet.rowIterator();
              while(rows.hasNext()){
                  HSSFRow row=(HSSFRow)rows.next();
                  rc++;
                  check="success";
                  Iterator cells=row.cellIterator();
                  while( cells.hasNext() ) {
                      HSSFCell cell = (HSSFCell) cells.next();
                      if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()){
                     	 if(cell.getColumnIndex()==4)
                          {
                          DataFormatter df=new DataFormatter();
                          if((df.formatCellValue(cell)).matches("^([0-9]{4})-([0-9]{2})-([0-9]{2})$"))
                                doj=df.formatCellValue(cell);
                         else
                         { message+="<p>Invalid Date format at row:"+rc+" and Column:5</p>";
                           check="error";
                         }
                          //out.print( "<h3>"+cvd+"</h3><br>");
                          }
                          else if(cell.getColumnIndex()==7)
                          {
                          cell.setCellType(Cell.CELL_TYPE_STRING);
                      if((cell.getStringCellValue()).matches("[0-9]{10}"))
                           mobile=cell.getStringCellValue();
                      else
                      {
                   	   message+="<p>Invalid Mobile Number format at row:"+rc+" and Column:8</p>";
                   	   check="error";
                      }
                          }
                          else if(cell.getColumnIndex()==5)
                          {
                       	   cell.setCellType(Cell.CELL_TYPE_STRING);
                              if((cell.getStringCellValue()).matches("[0-9]+"))
                                   level=Integer.parseInt(cell.getStringCellValue());
                              else
                              {
                           	   message+="<p>Invalid Level at row:"+rc+" and Column:6</p>";
                           	   check="error";
                              }
                          }
                          else if(cell.getColumnIndex()==6)
                          {
                       	   cell.setCellType(Cell.CELL_TYPE_STRING);
                              if((cell.getStringCellValue()).matches("[0-9]+"))
                                   layer=Integer.parseInt(cell.getStringCellValue());
                              else
                              {
                           	   message+="<p>Invalid Layer at row:"+rc+" and Column:7</p>";
                           	   check="error";
                              }
                          }

                      }
                      else if (HSSFCell.CELL_TYPE_STRING==cell.getCellType()){
                     	 if(cell.getColumnIndex()==0)
                      	{
                      		id=cell.getStringCellValue();
                  			String test;
                  			if(id.isEmpty())
                  				test="invalid";
                  			else
                  			{
                  				userdao userd=new userdao();
                  				userbean user=userd.getUserbyId(id);
                  				if(user==null)
                  				{
                  					test="valid";
                  				}
                  				else
                  					test="invalid";
                  			}
                  			if(test.equals("invalid"))
                  			{
                  				message+="<p>User Id is either null or already exists at row:"+rc+" and Column:1</p>";
                           	   check="error";
                  			}
                      	}
                      	if(cell.getColumnIndex()==8)
                      	{
                      		man1=cell.getStringCellValue();
                      		String test;
                  			if(man1.isEmpty())
                  				test="valid";
                  			else
                  			{
                  				userdao userd=new userdao();
                  				userbean user=userd.getUserbyId(man1);
                  				if(user==null)
                  				{
                  					test="invalid";
                  				}
                  				else
                  					test="valid";
                  			}
                  			if(test.equals("invalid"))
                  			{
                  				message+="<p>Invalid Manager Id at row:"+rc+" and Column:9</p>";
                           	   check="error";
                  			}
                      	}
                      	if(cell.getColumnIndex()==9)
                      	{
                      		man2=cell.getStringCellValue();
                      		String test;
                  			if(man2.isEmpty())
                  				test="valid";
                  			else
                  			{
                  				userdao userd=new userdao();
                  				userbean user=userd.getUserbyId(man2);
                  				if(user==null)
                  				{
                  					test="invalid";
                  				}
                  				else
                  					test="valid";
                  			}
                  			if(test.equals("invalid"))
                  			{
                  				message+="<p>Invalid Manager Id at row:"+rc+" and Column:10</p>";
                           	   check="error";
                  			}
                      	}
                        
                        
                         if(cell.getColumnIndex()==1)
                         {
                              if((cell.getStringCellValue()).matches("^[a-zA-Z\\s]+"))
                              name=cell.getStringCellValue();
                         else
                         {
                      	   message+="<p>Invalid name format at row:"+rc+" and Column:2</p>";
                      	   check="error";
                         }
                            
                         }
                         
                         if(cell.getColumnIndex()==2)
                              {
                              if((cell.getStringCellValue()).matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$"))
                              email=cell.getStringCellValue();
                         else
                         {
                      	   message+="<p>Invalid email format at row:"+rc+" and Column:3</p>";
                      	   check="error";
                         }
                         }
                         
                         if(cell.getColumnIndex()==3)
                             password=cell.getStringCellValue();
                         
                      } 
                  }
                  if(check.equals("success")){
                      java.sql.Date date_joined = java.sql.Date.valueOf(doj);
                      userdao userd=new userdao();
               	   userd.setUser(id, name, email, password, date_joined, level, layer, 0, mobile, man1, man2,false,true);
                      user_roledao user_roled=new user_roledao();
                      user_roled.setUserRolebyUId(id,"3");
                      }
                }
              }
                         else
                         {
                         	message+="Unsupported File format!Choose files of format .xls or .xlsx only!";
                         }
                     if(message==null)
                     {
                     	message="<p>Users added successfully</p>";
                     }
                        }
                        else
                        {
                        	message+="File upload unsuccessful";
                        }
                    }
                }

              request.setAttribute("errormsg", message);  
             RequestDispatcher rd=request.getRequestDispatcher("exceltodbmessage.jsp");
             rd.forward(request,response);
}
        catch(FileNotFoundException e)
        {
        	message+="The given file cannot be found!";
        	 request.setAttribute("errormsg", message);  
             RequestDispatcher rd=request.getRequestDispatcher("exceltodbmessage.jsp");
             rd.forward(request,response);
        }
        catch(Exception e){
   e.printStackTrace();
}
	}
	}
}
