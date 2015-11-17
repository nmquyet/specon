package vn.common.specs.meta;

import vn.common.specs.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author qunguyen
 */
public enum SpecificationValueType {

    STRING {
        @Override
        public SpecificationValue<?> createSpecificationValueFrom(String value) {
            return new StringValue(value);
        }

        @Override
        public List<Operator> operators() {
            return Arrays.asList(Operator.EQUAL, Operator.NOT, Operator.IN, Operator.NOT_IN);
        }
    },

    NUMERIC {
        @Override
        public SpecificationValue<?> createSpecificationValueFrom(String value) {
            return new NumericValue<Number>(value);
        }

        @Override
        public List<Operator> operators() {
            return Arrays.asList(Operator.EQUAL, Operator.NOT, Operator.GREATER_THAN, Operator.GREATER_THAN_EQUAL, Operator.LESS_THAN, Operator.LESS_THAN_EQUAL, Operator.IN, Operator.NOT_IN);
        }
    },

    DATETIME {
        @Override
        public SpecificationValue<?> createSpecificationValueFrom(String value) {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(value);
                return new DateValue(date);
            }
            catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format (yyyy-MM-dd'T'HH:mm:ssXXX)");
            }
        }

        @Override
        public List<Operator> operators() {
            return Arrays.asList(Operator.EQUAL, Operator.NOT, Operator.GREATER_THAN, Operator.GREATER_THAN_EQUAL, Operator.LESS_THAN, Operator.LESS_THAN_EQUAL);
        }
    },

    SELECT {
        @Override
        public SpecificationValue<?> createSpecificationValueFrom(String value) {
            return new StringValue(value);
        }

        @Override
        public List<Operator> operators() {
            return Arrays.asList(Operator.EQUAL, Operator.NOT);
        }
    },

    MULTI_SELECT {
        @Override
        public SpecificationValue<?> createSpecificationValueFrom(String value) {
            return new StringValue(value);
        }

        @Override
        public List<Operator> operators() {
            return Arrays.asList(Operator.IN, Operator.NOT_IN);
        }
    },

    BOOLEAN {
        @Override
        public SpecificationValue<?> createSpecificationValueFrom(String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<Operator> operators() {
            return Arrays.asList(Operator.EQUAL, Operator.NOT);
        }
    },

    CUSTOM {
        @Override
        public SpecificationValue<?> createSpecificationValueFrom(String value) {
            return null;
        }

        @Override
        public List<Operator> operators() {
            return null;
        }
    }

    ;

    public abstract SpecificationValue<?> createSpecificationValueFrom(String value);
    public abstract List<Operator> operators();

}
