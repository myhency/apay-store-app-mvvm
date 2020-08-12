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
             * "paymentId": 63,
             * "userId": 4,
             * "userName": "TestUser",
             * "storeId": 2,
             * "storeName": "TestStore",
             * "tokenSystemId": 1,
             * "tokenSystemName": "HiOT",
             * "amount": 1,
             * "paymentStatus": "REFUND_READY",
             * "identifier": "1597139519733",
             * "createdDate": "2020-08-11T10:08:43"
        * },
    * ],
    * "pageable": {
        * "sort": {
            * "sorted": true,
            * "unsorted": false,
            * "empty": false
        * },
        * "pageNumber": 0,
        * "pageSize": 10,
        * "offset": 0,
        * "paged": true,
        * "unpaged": false
    * },
    * "totalPages": 1,
    * "totalElements": 2,
    * "last": true,
    * "sort": {
        * "sorted": true,
        * "unsorted": false,
        * "empty": false
    * },
    * "numberOfElements": 2,
    * "first": true,
    * "size": 10,
    * "number": 0,
    * "empty": false
    * }
 * }
 */
public class PaymentListResponse {

    @Expose
    @SerializedName("data")
    private PaymentList data;

    public PaymentList getData() {
        return data;
    }

    public static class PaymentList {

        @Expose
        @SerializedName("content")
        private List<Content> contents;

        public List<Content> getContents() {
            return contents;
        }

        public static class Content {
            @Expose
            @SerializedName("paymentId")
            private Long paymentId;

            @Expose
            @SerializedName("userId")
            private Long userId;

            @Expose
            @SerializedName("userName")
            private String userName;

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
            @SerializedName("tokenSystemName")
            private String tokenSystemName;

            @Expose
            @SerializedName("amount")
            private Long amount;

            @Expose
            @SerializedName("paymentStatus")
            private String paymentStatus;

            @Expose
            @SerializedName("identifier")
            private String identifier;

            @Expose
            @SerializedName("createdDate")
            private Date createdDate;

            public Content(Long paymentId, Long userId, String userName, Long storeId, String storeName, Long tokenSystemId, String tokenSystemName, Long amount, String paymentStatus, String identifier, Date createdDate) {
                this.paymentId = paymentId;
                this.userId = userId;
                this.userName = userName;
                this.storeId = storeId;
                this.storeName = storeName;
                this.tokenSystemId = tokenSystemId;
                this.tokenSystemName = tokenSystemName;
                this.amount = amount;
                this.paymentStatus = paymentStatus;
                this.identifier = identifier;
                this.createdDate = createdDate;
            }

            public Long getPaymentId() {
                return paymentId;
            }

            public Long getUserId() {
                return userId;
            }

            public String getUserName() {
                return userName;
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

            public String getTokenSystemName() {
                return tokenSystemName;
            }

            public Long getAmount() {
                return amount;
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

    /**
     * "pageable": {
     *       "sort": {
     *         "sorted": true,
     *         "unsorted": false,
     *         "empty": false
     *       },
     *       "pageNumber": 0,
     *       "pageSize": 10,
     *       "offset": 0,
     *       "paged": true,
     *       "unpaged": false
     *     },
     *     "totalPages": 1,
     *     "totalElements": 2,
     *     "last": true,
     *     "sort": {
     *       "sorted": true,
     *       "unsorted": false,
     *       "empty": false
     *     },
     *     "numberOfElements": 2,
     *     "first": true,
     *     "size": 10,
     *     "number": 0,
     *     "empty": false
     *   }
     */
    public static class Pageable {

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

        public Pageable(int offset, int pageNumber, int pageSize, boolean paged, boolean unpaged, Sort sort) {
            this.offset = offset;
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.paged = paged;
            this.unpaged = unpaged;
            this.sort = sort;
        }

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

            public Sort(boolean sorted, boolean unsorted, boolean empty) {
                this.sorted = sorted;
                this.unsorted = unsorted;
                this.empty = empty;
            }

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

    @Expose
    @SerializedName("totalPages")
    private Long totalPages;

    @Expose
    @SerializedName("totalElements")
    private Long totalElements;

    @Expose
    @SerializedName("last")
    private boolean last;

    @Expose
    @SerializedName("sort")
    private Sort sort;

    public Pageable getPageable() {
        return pageable;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public Sort getSort() {
        return sort;
    }

    public Long getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public Long getSize() {
        return size;
    }

    public Long getNumber() {
        return number;
    }

    public boolean isEmpty() {
        return empty;
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

        public Sort(boolean sorted, boolean unsorted, boolean empty) {
            this.sorted = sorted;
            this.unsorted = unsorted;
            this.empty = empty;
        }

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

    @Expose
    @SerializedName("numberOfElements")
    private Long numberOfElements;

    @Expose
    @SerializedName("first")
    private boolean first;

    @Expose
    @SerializedName("size")
    private Long size;

    @Expose
    @SerializedName("number")
    private Long number;

    @Expose
    @SerializedName("empty")
    private boolean empty;
}
