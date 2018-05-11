package clinicApp.dto;

public class NotificationDto {

    private String content;

    public NotificationDto(String content) {
        this.content = content;
    }

    public NotificationDto() {}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
