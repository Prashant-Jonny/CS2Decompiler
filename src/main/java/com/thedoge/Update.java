package com.thedoge;

import com.wycody.cs2d.utils.ConfigParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created on 12/16/2015.
 *
 * DO NOT PUBLISH OR ADD TO GIT!
 */
public class Update {
    private static final String baseURL = "https://docs.google.com/spreadsheets/u/1/d/1EO8M0vj9tUT-HnNzdBPRrPVL_EOh3ZMsxcf1H82NMT8/export?format=tsv&id=1EO8M0vj9tUT-HnNzdBPRrPVL_EOh3ZMsxcf1H82NMT8&gid=";


    public enum Revision{
        _742(1820259069),
        _850(1133884859),
        _861( 170800423),


        ;
        private String id;

        Revision(long id){
            this.id = Long.toString(id,10);
        }


        public String getURL(){
            return baseURL + this.id;
        }
    }

    public static void updateFile(Revision revision){
        URL uri;
        try {
            uri = new URL(revision.getURL());
            InputStream is = uri.openConnection().getInputStream();

            File dstDir = new File("config");
            assert dstDir.exists() || dstDir.mkdir();
            FileOutputStream fos = new FileOutputStream("config/"+revision.toString().replace("_","")+".tsv");
            int b;
            while((b=is.read()) != -1) {
                fos.write((byte)b);
            }
        } catch (Exception ex) {
            Logger.getLogger(ConfigParser.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
