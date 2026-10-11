ALTER TABLE orders
    DROP COLUMN customer_id,
    DROP COLUMN created_at,
    ADD COLUMN product_id BIGINT NOT NULL,
    ADD COLUMN quantity INTEGER NOT NULL;
