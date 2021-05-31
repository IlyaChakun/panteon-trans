


INSERT INTO cargo_stowage_methods(id, stowage_method_name)
VALUES (1, 'полная'),
       (2, 'частичная') ON CONFLICT DO NOTHING;



INSERT INTO truck_types(id, truck_type_name)
VALUES (1, 'цистерна пищ'),
       (2, 'зерновоз') ON CONFLICT DO NOTHING;



INSERT INTO cargo_types(id, type_name)
VALUES (1, 'Пища'),
       (2, 'другое') ON CONFLICT DO NOTHING;
