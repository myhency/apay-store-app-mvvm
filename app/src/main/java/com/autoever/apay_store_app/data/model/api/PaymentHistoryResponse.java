package com.autoever.apay_store_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * {
 * "paymentHistoryId": 15,
 * "paymentId": 11,
 * "userId": 4,
 * "storeId": 2,
 * "userName": "TestUser",
 * "tokenSystemId": 1,
 * "tokenSystemName": "HiOT",
 * "amount": 10,
 * "storeBalance": 10030,
 * "paymentStatus": "PAY_COMPLETE",
 * "identifier": "v3Test1",
 * "createdDate": "2020-08-06T02:51:28"
 * },
 */
public class PaymentHistoryResponse {

    @Expose
    @SerializedName("data")
    private PaymentHistory data;

    public PaymentHistory getData() {
        return data;
    }

    public static class PaymentHistory {

        @Expose
        @SerializedName("content")
        private List<Content> contents;

        public List<Content> getContents() {
            return contents;
        }

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
            @SerializedName("userName")
            private String userName;

            @Expose
            @SerializedName("tokenSystemId")
            private Long tokenSystemId;

            @Expose
            @SerializedName("tokenSystemName")
            private String tokenSystemName;

            @Expose
            @SerializedName("amount")
            private Long amount;

            @Expose
            @SerializedName("storeBalance")
            private Long storeBalance;

            @Expose
            @SerializedName("paymentStatus")
            private String paymentStatus;

            @Expose
            @SerializedName("identifier")
            private String identifier;

            @Expose
            @SerializedName("createdDate")
            private Date createdDate;

            public Content(Long paymentHistoryId, Long paymentId, Long userId, Long storeId, String userName, Long tokenSystemId, String tokenSystemName, Long amount, Long storeBalance, String paymentStatus, String identifier, Date createdDate) {
                this.paymentHistoryId = paymentHistoryId;
                this.paymentId = paymentId;
                this.userId = userId;
                this.storeId = storeId;
                this.userName = userName;
                this.tokenSystemId = tokenSystemId;
                this.tokenSystemName = tokenSystemName;
                this.amount = amount;
                this.storeBalance = storeBalance;
                this.paymentStatus = paymentStatus;
                this.identifier = identifier;
                this.createdDate = createdDate;
            }

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

            public String getUserName() {
                return userName;
            }

            public Long getTokenSystemId() {
                return tokenSystemId;
            }

            public String getTokenSystemName() {
                return tokenSystemName;
            }

            public Long getAmount() {
                return amount;
            }

            public Long getStoreBalance() {
                return storeBalance;
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
