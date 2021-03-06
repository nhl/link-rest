package io.agrest.cayenne.cayenne.main.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.EntityProperty;
import org.apache.cayenne.exp.property.PropertyFactory;

import io.agrest.cayenne.cayenne.main.E1;
import io.agrest.cayenne.cayenne.main.E15;

/**
 * Class _E15E1 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _E15E1 extends BaseDataObject {

    private static final long serialVersionUID = 1L;

    public static final String E15_ID_PK_COLUMN = "e15_id";
    public static final String E1_ID_PK_COLUMN = "e1_id";

    public static final EntityProperty<E1> E1 = PropertyFactory.createEntity("e1", E1.class);
    public static final EntityProperty<E15> E15 = PropertyFactory.createEntity("e15", E15.class);


    protected Object e1;
    protected Object e15;

    public void setE1(E1 e1) {
        setToOneTarget("e1", e1, true);
    }

    public E1 getE1() {
        return (E1)readProperty("e1");
    }

    public void setE15(E15 e15) {
        setToOneTarget("e15", e15, true);
    }

    public E15 getE15() {
        return (E15)readProperty("e15");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "e1":
                return this.e1;
            case "e15":
                return this.e15;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "e1":
                this.e1 = val;
                break;
            case "e15":
                this.e15 = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.e1);
        out.writeObject(this.e15);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.e1 = in.readObject();
        this.e15 = in.readObject();
    }

}
