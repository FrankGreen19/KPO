package project.model;

import project.resource.SubscribeResource;

import java.util.Date;

public class Subscribe extends BaseEntity {

    private int userId;
    private int typeId;
    private Date subDateBegin;

    public Subscribe(Integer id) {
        super(id);
    }

    public Subscribe(Integer id, int userId, int typeId, Date subDateBegin) {
        super(id);
        this.userId = userId;
        this.typeId = typeId;
        this.subDateBegin = subDateBegin;
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

    public SubscribeResource toResource() {
        return new SubscribeResource(
                this.getId(),
                this.userId,
                this.typeId,
                this.subDateBegin
        );
    }
}
