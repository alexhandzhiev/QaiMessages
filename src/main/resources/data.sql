DROP TABLE IF EXISTS message;

CREATE TABLE message (
  id INT AUTO_INCREMENT PRIMARY KEY,
  message_payload VARCHAR(160),
  message_type VARCHAR(20) NOT NULL,
  created_at VARCHAR(30)
);