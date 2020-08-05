package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * {
 * "data": {
 * "content": [
 * {
 * "paymentHistoryId": 538,
 * "paymentId": 537,
 * "userId": 4,
 * "storeId": 2,
 * "storeName": "TestStore",
 * "tokenSystemId": 1,
 * "amount": 1,
 * "userBalance": 101957,
 * "paymentStatus": "PAY_COMPLETE",
 * "identifier": "1595573578306",
 * "createdDate": "2020-07-24T06:52:58"
 * }
 * ],
 * "pageable": {
 * "sort": {
 * "sorted": true,
 * "unsorted": false,
 * "empty": false
 * },
 * "offset": 0,
 * "pageNumber": 0,
 * "pageSize": 10,
 * "paged": true,
 * "unpaged": false
 * },
 * "totalPages": 10,
 * "totalElements": 95,
 * "last": false,
 * "sort": {
 * "sorted": true,
 * "unsorted": false,
 * "empty": false
 * },
 * "size": 10,
 * "number": 0,
 * "numberOfElements": 10,
 * "first": true,
 * "empty": false
 * }
 * }
 */
public class CardUseHistoryResponse {

    @Expose
    @SerializedName("data")
    private CardUseHistory data;

    public CardUseHistory getData() {
        return data;
    }

    public static class CardUseHistory {

        @Expose
        @SerializedName("content")
        private List<Content> contents;

        public List<Content> getContents() {
            return contents;
        }

        /*
         * {
         * "paymentHistoryId": 538,
         * "paymentId": 537,
         * "userId": 4,
         * "storeId": 2,
         * "storeName": "TestStore",
         * "tokenSystemId": 1,
         * "amount": 1,
         * "userBalance": 101957,
         * "paymentStatus": "PAY_COMPLETE",
         * "identifier": "1595573578306",
         * "createdDate": "2020-07-24T06:52:58"
         * }
         */
        public static class Content {

            @Expose
            @SerializedName("paymentHistoryId")
            private Long paymentHistoryId;

            @Expose
            @SerializedName("paymentId")
            private Long paymentId;

            @Expose
            @SerializedName("userId")
            private Long userId;

            @Expose
            @SerializedName("storeId")
            private Long storeId;

            @Expose
            @SerializedName("storeName")
            private String storeName;

            @Expose
            @SerializedName("tokenSystemId")
            private Long tokenSystemId;

            @Expose
            @SerializedName("amount")
            private Long amount;

            @Expose
            @SerializedName("userBalance")
            private Long userBalance;

            @Expose
            @SerializedName("paymentStatus")
            private String paymentStatus;

            @Expose
            @SerializedName("identifier")
            private String identifier;

            @Expose
            @SerializedName("createdDate")
            private Date createdDate;

            public Long getPaymentHistoryId() {
                return paymentHistoryId;
            }

            public Long getPaymentId() {
                return paymentId;
            }

            public Long getUserId() {
                return userId;
            }

            public Long getStoreId() {
                return storeId;
            }

            public String getStoreName() {
                return storeName;
            }

            public Long getTokenSystemId() {
                return tokenSystemId;
            }

            public Long getAmount() {
                return amount;
            }

            public Long getUserBalance() {
                return userBalance;
            }

            public String getPaymentStatus() {
                return paymentStatus;
            }

            public String getIdentifier() {
                return identifier;
            }

            public Date getCreatedDate() {
                return createdDate;
            }
        }
    }

    @Expose
    @SerializedName("pageable")
    private Pageable pageable;

    public static class Pageable {
            /*
            "pageable": {
              "sort": {
                "sorted": true,
                "unsorted": false,
                "empty": false
              },
              "offset": 10,
              "pageNumber": 1,
              "pageSize": 10,
              "paged": true,
              "unpaged": false
            },
                     */

        @Expose
        @SerializedName("offset")
        private int offset;

        @Expose
        @SerializedName("pageNumber")
        private int pageNumber;

        @Expose
        @SerializedName("pageSize")
        private int pageSize;

        @Expose
        @SerializedName("paged")
        private boolean paged;

        @Expose
        @SerializedName("unpaged")
        private boolean unpaged;

        @Expose
        @SerializedName("sort")
        private Sort sort;

        public int getOffset() {
            return offset;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public boolean isPaged() {
            return paged;
        }

        public boolean isUnpaged() {
            return unpaged;
        }

        public Sort getSort() {
            return sort;
        }


        public static class Sort {

            @Expose
            @SerializedName("sorted")
            private boolean sorted;

            @Expose
            @SerializedName("unsorted")
            private boolean unsorted;

            @Expose
            @SerializedName("empty")
            private boolean empty;

            public boolean isSorted() {
                return sorted;
            }

            public boolean isUnsorted() {
                return unsorted;
            }

            public boolean isEmpty() {
                return empty;
            }
        }
    }
}
