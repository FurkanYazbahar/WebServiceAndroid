package com.haberservisi.haberservisi;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

public class NewsWdslService {
    static SoapObject soapObject;
    static SoapSerializationEnvelope soapSerializationEnvelope;
    static HttpTransportSE httpTransportSE;
    static String WSDL_URL = "http://192.168.1.93:8080/denemeWebServisi/services/NewsService?wsdl";

    public static String increaseLike(String title) {
        return increase(title, "increaseLike");
    }

    public static String increaseDislike(String title) {
        return increase(title, "increaseDislike");
    }

    private static String increase(String title, String method_name) {
        String returnedData = "";
        String METHOD_NAME = method_name;
        String SOAP_ACTION = WSDL_URL + "/" + METHOD_NAME;

        soapObject = new SoapObject(WSDL_URL, METHOD_NAME);
        soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        soapObject.addProperty("title", title);

        httpTransportSE = new HttpTransportSE(WSDL_URL);
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) soapSerializationEnvelope.getResponse();
            returnedData = soapPrimitive.toString();
            System.out.println(soapPrimitive.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnedData;
    }

    public static News getNews(String title) {
        News news = new News();
        String METHOD_NAME = "getNews";
        String SOAP_ACTION = WSDL_URL + "/" + METHOD_NAME;

        soapObject = new SoapObject(WSDL_URL, METHOD_NAME);
        soapObject.addProperty("title", title);

        soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE = new HttpTransportSE(WSDL_URL);
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);

            // XXX: soapobject gavattır. aksini iddia eden soapobjecttir!
            SoapObject response = (SoapObject) soapSerializationEnvelope.getResponse();

            if(response.getProperty("id") == null){
                return news;
            }

            news.setId     (Integer.valueOf(String.valueOf(response.getProperty("id"))));
            news.setTitle  (String.valueOf(response.getProperty("title")));
            news.setContent(String.valueOf(response.getProperty("content")));
            news.setType   (String.valueOf(response.getProperty("type")));
            news.setDate   (String.valueOf(response.getProperty("date")));
            news.setLike   (Integer.valueOf(String.valueOf(response.getProperty("like"))));
            news.setDislike(Integer.valueOf(String.valueOf(response.getProperty("dislike"))));
            news.setView   (Integer.valueOf(String.valueOf(response.getProperty("view"))));

            if(response.getProperty("picture") != null)
                news.setPicture((byte[])response.getProperty("picture"));

            if(response.getProperty("picture") != null)
                news.setPictureLink(String.valueOf(response.getProperty("pictureLink")));

        } catch (Exception ex) {
            Log.e("NewsWdslService", "Error: " + ex);
            ex.printStackTrace();
        }
        return news;
    }

    public static ArrayList<String> getTypes() {
        ArrayList<String> types = new ArrayList<String>();
        String METHOD_NAME  = "getTypes";
        String SOAP_ACTION  = WSDL_URL + "/" + METHOD_NAME;

        soapObject = new SoapObject(WSDL_URL, METHOD_NAME);
        soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE = new HttpTransportSE(WSDL_URL);
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);
            SoapObject response = (SoapObject) soapSerializationEnvelope.getResponse();

            for(int i = 0; i < response.getPropertyCount(); i++){
                types.add(String.valueOf(response.getProperty(i)));
            }

            if(response.getPropertyCount() == 0){
                types.add("No Type");
            }

        } catch (Exception ex) {
            Log.e("DENEME", "Error: " + ex);
        }
        return types;
    }
//    public LinkedList<String> getTitles(String types, int start_index, int finish_index)

    public static ArrayList<String> getTitles(String types, String start_index, String finish_index) {
        ArrayList<String> titles = new ArrayList<String>();
        String METHOD_NAME  = "getTitles";
        String SOAP_ACTION  = WSDL_URL + "/" + METHOD_NAME;

        soapObject = new SoapObject(WSDL_URL, METHOD_NAME);
        soapObject.addProperty("types", types);
        soapObject.addProperty("start_index",  Integer.valueOf(start_index));
        soapObject.addProperty("finish_index", Integer.valueOf(finish_index));


        soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE = new HttpTransportSE(WSDL_URL);
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);
            SoapObject response = (SoapObject) soapSerializationEnvelope.getResponse();

            for(int i = 0; i < response.getPropertyCount(); i++){
                //HaberTasks.getNews n = new HaberTasks.getNews();
                //n.execute(titles.get(i));
                titles.add(String.valueOf(response.getProperty(i)));
            }

        } catch (Exception ex) {
            Log.e("DENEME", "Error: " + ex);
        }

        return titles;
    }
}
