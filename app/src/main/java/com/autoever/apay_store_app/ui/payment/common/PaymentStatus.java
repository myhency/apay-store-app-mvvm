package com.autoever.apay_store_app.ui.payment.common;

import java.util.Arrays;
import java.util.List;

public enum PaymentStatus {

    PAY_COMPLETE("결제", "PAY_COMPLETE", false),
    PAY_FAIL("결제실패", "PAY_FAIL", false),
    PAY_READY("결제대기", "PAY_READY", false),
    PAY_READY_CANCEL("결제대기취소", "PAY_READY_CANCEL",false),
    PAY_CANCEL("결제취소", "PAY_CANCEL", true),
    REFUND_COMPLETE("환전완료", "REFUND_COMPLETE", false),
    REFUND_READY("결제취소 요청", "REFUND_READY", false);

    private String displayValue;
    private String status;
    private boolean isIncome;

    PaymentStatus(String displayValue, String status, boolean isIncome) {
        this.displayValue = displayValue;
        this.status = status;
    }

    public static PaymentStatus find(String status) {
        List<PaymentStatus> values = Arrays.asList(PaymentStatus.values());

        return values.stream()
                .filter(value -> value.getStatus().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getStatus() {
        return status;
    }

    public boolean isIncome() {
        return isIncome;
    }
}
