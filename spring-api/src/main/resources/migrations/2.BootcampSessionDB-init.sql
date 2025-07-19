CREATE TABLE IF NOT EXISTS public.users
(
    id       bigserial primary key,
    username text

);


CREATE TABLE IF NOT EXISTS public.chats
(
    id      bigserial primary key,
    user_id bigint not null,
    title   text,
    created_at timestamp default now()
);


CREATE TABLE IF NOT EXISTS public.messages
(
    id        bigserial primary key,
    chat_id   bigint not null,
    user_id   bigint not null,
    text      text,
    sender    text,
    is_user_message boolean default false,
    created_at timestamp default now()
);

-- CREATE TABLE IF NOT EXISTS public.chat_users
-- (
--     chat_id bigint not null,
--     user_id bigint not null,
--     primary key (chat_id, user_id)
-- );


-- .....1 month later

ALTER TABLE public.users
    ADD COLUMN IF NOT EXISTS email text unique;

ALTER TABLE public.users
    ADD COLUMN IF NOT EXISTS password text;

ALTER TABLE public.users
    ADD COLUMN IF NOT EXISTS name text;

ALTER TABLE public.users
    ADD COLUMN IF NOT EXISTS role text;


INSERT INTO public.users (username, email, password, name, role)
VALUES ('john_doe', 'john_doe@example.com', '1234', 'John Doe', 'SIMPLE_USER'),
       ('jane_doe', 'jane_doe@example.com', '5678', 'Jane Doe', 'ADMINISTRATOR_USER')
    ON CONFLICT (email) DO NOTHING;



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


INSERT INTO public.messages (chat_id, user_id, text, sender, is_user_message)
VALUES  (2, 1, 'Υπάρχουν εργαλεία για να οργανώσω καλύτερα τον χρόνο στο project του;', 'user', true),
        (2, 1, 'Φυσικά! Υπάρχουν εξαιρετικά εργαλεία που μπορούν να σε βοηθήσουν να οργανώσεις τον χρόνο σου και να διαχειριστείς αποτελεσματικά το project σου. Εδώ είναι μερικά από τα πιο δημοφιλή και χρήσιμα:', 'chat_gpt', false),
        (2, 1, '1. Trello: Ένα εργαλείο διαχείρισης έργων που σου επιτρέπει να δημιουργήσεις πίνακες, λίστες και κάρτες για να οργανώσεις τις εργασίες σου.', 'chat_gpt', false),
        (2, 1, '2. Asana: Ένα άλλο δημοφιλές εργαλείο διαχείρισης έργων που σου επιτρέπει να παρακολουθείς την πρόοδο των εργασιών και να συνεργάζεσαι με την ομάδα σου.', 'chat_gpt', false),
        (2, 1, '3. Notion: Ένα πολυλειτουργικό εργαλείο που συνδυάζει σημειώσεις, βάσεις δεδομένων και διαχείριση έργων σε ένα.', 'chat_gpt', false),
        (2, 1, '4. Microsoft Project: Ένα επαγγελματικό εργαλείο διαχείρισης έργων που προσφέρει προηγμένες δυνατότητες προγραμματισμού και παρακολούθησης.', 'chat_gpt', false),
        (2, 1, '5. Google Calendar: Για τον προγραμματισμό συναντήσεων και την οργάνωση του χρόνου σου.', 'chat_gpt', false);


UPDATE public.messages
SET is_user_message = true
WHERE id = 1;