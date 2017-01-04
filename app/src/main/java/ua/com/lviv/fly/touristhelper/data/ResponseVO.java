package ua.com.lviv.fly.touristhelper.data;

import java.util.Arrays;
import java.util.List;

import ua.com.lviv.fly.touristhelper.data.base.AbstractVO;

/**
 * Created by PASHA on 10.12.2016.
 */

public class ResponseVO extends AbstractVO<String> {
   private String[] html_attributions;
   private List<ResultsVO> results;
   private String status;

   public String[] getHtml_attributions() {
      return html_attributions;
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
              "html_attributions=" + Arrays.toString(html_attributions) +
              ", results=" + results +
              ", status='" + status + '\'' +
              '}';
   }
}
