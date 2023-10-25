package edu.miu.waa.waaauctionsystem.models;

import lombok.Data;

@Data
public class BidConvertorObject {
      private Long productId;
      private String email;
      private float deposit;
}
