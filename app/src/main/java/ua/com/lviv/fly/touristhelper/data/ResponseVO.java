package ua.com.lviv.fly.touristhelper.data;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

import ua.com.lviv.fly.touristhelper.data.base.AbstractVO;

/**
 * Created by PASHA on 10.12.2016.
 */

public class ResponseVO extends AbstractVO<String> {
   @SerializedName("html_attributions")
   private String[] htmlAttributions;
   private List<ResultsVO> results;
   private String status;

   public String[] getHtmlAttributions() {
      return htmlAttributions;
   }

   public List<ResultsVO> getResults() {
      return results;
   }

   public String getStatus() {
      return status;
   }

   @Override
   public String toString() {
      return "ResponseVO{" +
              "htmlAttributions=" + Arrays.toString(htmlAttributions) +
              ", results=" + results +
              ", status='" + status + '\'' +
              '}';
   }
}
