/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.project.bean.UserBean;
import com.project.db.DBConnect;

import java.util.Base64;
public class UserDao {

	PreparedStatement pst;
	ResultSet rs;
	Boolean resultStatus=Boolean.FALSE;
	Connection con=DBConnect.getConnection();

	public boolean userRegistration(UserBean bean)
	{
		try
		{
			String sql="Select * from tbluser where user_email=?";
			pst = con.prepareStatement(sql);
			pst.setString(1,bean.getEmail());
			rs=pst.executeQuery();
			Boolean b=rs.next();

			if(b == true)
			{
				System.out.println("Record already exist");
			}

			else
			{

				String SQL="insert into tbluser(user_name, user_address, user_email, user_mob, user_password) values(?,?,?,?,?)"; 

				PreparedStatement pstmt=con.prepareStatement(SQL);
				pstmt.setString(1, bean.getUname());
				pstmt.setString(2, bean.getAddress());
				pstmt.setString(3, bean.getEmail());
				pstmt.setString(4, bean.getMobNo());
				pstmt.setString(5, bean.getPassword());

				int index=pstmt.executeUpdate();

				if(index>0)
				{
					resultStatus=Boolean.TRUE;
				}
				else
				{
					resultStatus=Boolean.FALSE;	
				}


			}
		}

		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultStatus;

	}



	public  boolean loginCheck(UserBean bean)
	{
		 String encodedString = "",DecodedString;
		 byte[] base64decodedBytes;

		try {
			//String sql="Select * from tbluser where user_email=? and user_password=?";
			String sql = "select user_password from tbluser where user_email = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1,bean.getEmail());
			
			//pst.setString(2,bean.getPassword());
			ResultSet rs=pst.executeQuery();
			//ResultSetMetaData rms=rs.getMetaData();
			resultStatus=rs.next();
			if(resultStatus==Boolean.TRUE) {
				//System.out.println("rs = True");
				rs=pst.executeQuery();
					while(rs.next()) {
						//System.out.println(rs.getObject(1));
						encodedString = rs.getObject(1).toString();
						//System.out.println("encodedString = "+encodedString);
						break;
					}
						
					
					
					try {
						
						//encodedString = Base64.getEncoder().encodeToString(encodedString.getBytes("utf-8"));
						base64decodedBytes = Base64.getDecoder().decode(encodedString);
						DecodedString = new String(base64decodedBytes, "utf-8");
						//System.out.println(" DecodedString = "+DecodedString);
						if(DecodedString.equals(bean.getPassword()))
							resultStatus = Boolean.TRUE;
						else 
							resultStatus = Boolean.FALSE;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
						
			}
			else
				resultStatus = Boolean.FALSE;
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return resultStatus;


	}


}
