package ua.com.lviv.fly.touristhelper.data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PASHA on 10.12.2016.
 */

public class ResponseVO {
   private String[] html_attributions;
   private Results[] results;
   private String status;

   @Override
   public String toString() {
      return "ResponseVO{" +
              "html_attributions=" + Arrays.toString(html_attributions) +
              ", results=" + Arrays.toString(results) +
              ", status='" + status + '\'' +
              '}';
   }
}
