package project.resource;

import project.model.Subscribe;

import java.util.Date;

public class SubscribeResource extends BaseResource {

    private int id;
    private int userId;
    private int typeId;
    private Date subDateBegin;

    public SubscribeResource() {
    }

    public SubscribeResource(int id, int userId, int typeId, Date subDateBegin) {
        this.id = id;
        this.userId = userId;
        this.typeId = typeId;
        this.subDateBegin = subDateBegin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Date getSubDateBegin() {
        return subDateBegin;
    }

    public void setSubDateBegin(Date subDateBegin) {
        this.subDateBegin = subDateBegin;
    }

    public Subscribe toEntity() {
        return new Subscribe (
                this.id,
                this.userId,
                this.typeId,
                this.subDateBegin
        );
    }
}
