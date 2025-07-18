CREATE TABLE IF NOT EXISTS public.users(
                                           id bigserial PRIMARY KEY,
                                           username text,
                                           email text,
                                           password text,
                                           name text,
                                           role text

);

CREATE TABLE IF NOT EXISTS public.chats(
                                           id bigserial PRIMARY KEY,
                                           user_id bigint,
                                           title text
);

-- test data or development data
INSERT INTO public.users (username, email, password, name, role)
VALUES ('john_doe', 'john_doe@example.com', '1234', 'John Doe', 'SIMPLE_USER'),
       ('jane_doe', 'jane_doe@example.com', '5678', 'Jane Doe', 'ADMINISTRATOR_USER');

ALTER TABLE public.users
    ADD CONSTRAINT unique_email UNIQUE (email);

-- Foreign Key Constraints με CASCADE
CREATE TABLE IF NOT EXISTS public.chats (
                                            id bigserial PRIMARY KEY,
                                            user_id bigint,
                                            title text,
                                            FOREIGN KEY (user_id) REFERENCES users(id)
                                                ON UPDATE CASCADE
                                                ON DELETE CASCADE
);
-- when we delete a user, all their chats will be deleted as well
-- when we update a user, all their chats will be updated as well
DELETE FROM public.users WHERE email = 'john_doe@example.com';

-- DDL IS THE SCHEMA DEFINITION LANGUAGE
-- DML IS THE DATA MANIPULATION LANGUAGE
-- ALTER TABLE public.users
--     ALTER COLUMN name TYPE varchar(255);

-- ALTER TABLE public.chats
--     ADD COLUMN IF NOT EXISTS gender bigint unique;



-- INSERT INTO public.users (username, email, password, name, role)
-- VALUES ('john_doe', 'john_doe@example.com', '1234', 'John Doe', 'SIMPLE_USER'),
--        ('jane_doe', 'jane_doe@example.com', '5678', 'Jane Doe', 'ADMINISTRATOR_USER')
-- ON CONFLICT (email) DO NOTHING;



INSERT INTO public.chats (user_id, title)
VALUES ((SELECT id FROM public.users WHERE email = 'john_doe@example.com'), 'Daily Standup'),
       ((SELECT id FROM public.users WHERE email = 'john_doe@example.com'), 'Project Planning'),
       ((SELECT id FROM public.users WHERE email = 'john_doe@example.com'), 'Budget Review'),
       ((SELECT id FROM public.users WHERE email = 'john_doe@example.com'), 'Retrospective'),
       ((SELECT id FROM public.users WHERE email = 'john_doe@example.com'), 'Design Discussion'),
       ((SELECT id FROM public.users WHERE email = 'jane_doe@example.com'), 'Daily Standup'),
       ((SELECT id FROM public.users WHERE email = 'jane_doe@example.com'), 'Admin Team Meeting'),
       ((SELECT id FROM public.users WHERE email = 'jane_doe@example.com'), 'User Feedback'),
       ((SELECT id FROM public.users WHERE email = 'jane_doe@example.com'), 'Roadmap Session'),
       ((SELECT id FROM public.users WHERE email = 'jane_doe@example.com'), 'Marketing Sync');
--
--
-- ALTER TABLE public.chats
--     ADD COLUMN IF NOT EXISTS created_at timestamp with time zone DEFAULT now();

-- SELECT nextval('public.users_id_seq') AS next_user_id,
--        nextval('public.chats_id_seq') AS next_chat_id;

-- ALTER TABLE public.users
-- ADD COLUMN IF NOT EXISTS surname text;

-- ALTER TABLE public.chats
-- ADD COLUMN IF NOT EXISTS created_at timestamp with time zone DEFAULT now(),
-- ADD COLUMN IF NOT EXISTS updated_at timestamp with time zone DEFAULT now();

-- CREATE TABLE IF NOT EXISTS public.messages(
--     id bigserial PRIMARY KEY,
--     chat_id bigint,
--     user_id bigint,
--     content text,
--     created_at timestamp with time zone DEFAULT now()
-- );









select * from users;
select * from chats;

select u.id, u.username, c.id as chat_id, c.title
from public.users u inner join public.chats c on  u.id = c.user_id
where title ilike '%a%';


select u.username, count(1) as chat_count
from public.users u inner join public.chats c on  u.id = c.user_id
where title ilike '%a%'
group by u.username
having count(1) > 1
order by chat_count ASC;