package edu.neu.cs5200.myCar.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.omg.IOP.Encoding;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.neu.cs5200.myCar.models.*;


public class makesDAO {

	private String key="87sx47e6prqw9ebadnn65bft";
	private final String FindAllmakes="https://api.edmunds.com/api/vehicle/v2/makes?fmt=json&api_key=apikey";
	private final String Findbymakes="http://api.edmunds.com/api/vehicle/v2/makes?fmt=json&state=used&api_key=87sx47e6prqw9ebadnn65bft";
	private final String Findbymodel="https://api.edmunds.com/api/vehicle/v2/makes/models?fmt=json&state=used&api_key=87sx47e6prqw9ebadnn65bft";
	private final String Findbyyear="https://api.edmunds.com/api/vehicle/v2/makes/models/years?fmt=json&api_key=87sx47e6prqw9ebadnn65bft";
	private final String findbystyle="https://api.edmunds.com/api/vehicle/v2/styles/styleid/engines?availability=standard&fmt=json&api_key=87sx47e6prqw9ebadnn65bft";
	private final String findphoto="http://api.edmunds.com/v1/api/vehiclephoto/service/findphotosbystyleid?styleId=STYLE_ID&api_key=87sx47e6prqw9ebadnn65bft&fmt=json&comparator=simple";
	public makesholder findAllMakes()
			{
				String url=FindAllmakes.replace("apikey",key);;
				ObjectMapper mapper=new ObjectMapper();
				String json=getJsonStringForUrl(url);

				System.out.println(json);
				
				try {
					return mapper.readValue(json, makesholder.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
		
			}
			
	public modelsholder findmodelbymakes(String makesname)
	{	
		makesname=URLEncoder.encode(makesname);//make space available
		makesname = makesname.replace("+", "%20"); 
		String url=Findbymakes.replace("makes",makesname);
		ObjectMapper mapper=new ObjectMapper();
		String json=getJsonStringForUrl(url);
		if(json==null)
		{
			return null;
		}
		try {
			return mapper.readValue(json, modelsholder.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	
	
	public yearsholder findbymodel(String modelname,String makesname)
	{
		//exactly same as by title
		makesname=URLEncoder.encode(makesname);//make space available
		String temp0= makesname.replace("+", "%20"); 
		String temp=Findbymodel.replace("makes",temp0);
		String modelsname=URLEncoder.encode(modelname);//make space available
		String temp1= modelsname.replace("+", "%20");
		String url=temp.replace("models",temp1);
		ObjectMapper mapper=new ObjectMapper();
		String json=getJsonStringForUrl(url);
		try {
			return mapper.readValue(json, yearsholder.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//convert from json to class
		return null;
	}
		
	public stylesholder findbyyear(String modelname,String makesname,String years)
	{
		//exactly same as by title
		makesname=URLEncoder.encode(makesname);//make space available
		String temp0= makesname.replace("+", "%20"); 
		String temp=Findbyyear.replace("makes",temp0);
		String modelsname=URLEncoder.encode(modelname);//make space available
		String temp1= modelsname.replace("+", "%20");
		String temp2=temp.replace("models",temp1);
		String url=temp2.replace("years",years);
		ObjectMapper mapper=new ObjectMapper();
		String json=getJsonStringForUrl(url);
		try {
			//System.out.println(getJsonStringForUrl(url));
			return mapper.readValue(json, stylesholder.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//convert from json to class
		
		//System.out.println(getJsonStringForUrl(url));
		return null;
	}
	

	public engineholder findbystyleid(String styleid)
	{
		String url=findbystyle.replace("styleid",styleid);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=getJsonStringForUrl(url);
		try {
			return mapper.readValue(json, engineholder.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//convert from json to class
		
//		System.out.println(getJsonStringForUrl(url));
		return null;
	}
	
	
	
	
	public photo[] findphotobystyleid(String styleid)
	{
		//exactly same as by title
		String url=findphoto.replace("STYLE_ID",styleid);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=getJsonStringForUrl(url);
		try {
			return mapper.readValue(json, photo[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//convert from json to class
		
//		System.out.println(getJsonStringForUrl(url));
		return null;
	}
	
	
	
	
	private String getJsonStringForUrl(String urlStr) {
		try {
			URL url=new URL(urlStr);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream in = connection.getInputStream();
			InputStreamReader isr= new InputStreamReader(in);
			BufferedReader reader=new BufferedReader(isr);
			String out;
			StringBuffer json=new StringBuffer();
			while((out=reader.readLine())!=null)
			{
				//System.out.println(out);
				json.append(out);
			}
			return json.toString();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

			
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		makesDAO client=new makesDAO();
		/*makesholder makesh =client.findAllMakes();
		for(makes make:makesh.getMake())
		{
			System.out.println(make.getId());
			System.out.println(make.getName());
		}*/
		
/*		modelsholder modelsh =client.findmodelbymakes("Alfa Romeo");
		if(modelsh==null)*/
//		for(models model:modelsh.getModel())
//		{
//			System.out.println(model.getId());
//			System.out.println(model.getName());
//		}
		
		/*yearsholder yearsh =client.findbymodel("Discovery Series II","Land Rover");
		for(years year:yearsh.getYear())
		{
			System.out.println(year.getId());
			System.out.println(year.getYear());
		}*/
		
		
		/*stylesholder stylesh =client.findbyyear("rx350","lexus","2011");
		for(styles style:stylesh.getStyle())
		{
			System.out.println(style.getId());
			System.out.println(style.getName());
		}*/
		/*	engineholder enginesh =client.findbystyleid("200477465");
			for(engine engines:enginesh.getEngines())
			{
			System.out.println(engines.getEquipmentType());
			}
**/
	//	photo[] photos=client.findphotobystyleid("200429368");
//		String styleid="200493810";
//		photo[] photos=client.findphotobystyleid(styleid);
//
//		photo p=new photo();
//		p=photos[0];
//		String pp[]=p.getPhotoSrcs();
//		System.out.println(pp[0]);
//		for(photo p: photos)
//		{
//			for(String pp: p.getPhotoSrcs())
//			{
//				System.out.println(pp);
//			}
//		}
	}

}