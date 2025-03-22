
create table users (
                       id uuid primary key default uuid_generate_v4(),
                       email varchar(255) not null,
                       password varchar(100) not null,
                       created_at timestamp default current_timestamp,
                        rol smallint not null default 0
);

create table profile (
                         id uuid not null primary key,
                         nickname varchar(100) not null,
                         avatar varchar(255) not null,
                         born_date timestamp,
                         ban_at timestamp,
                         is_new_user boolean default true not null,

                         constraint fk_users
                             foreign key (id)
                                 references users(id)
                                 on delete cascade
                                 on update cascade
);

create table premiun (
                         profile_id uuid not null,
                         by_at timestamp default current_timestamp,
                         rol smallint not null default 0,

                         constraint fk_users_premium
                             foreign key (profile_id)
                                 references profile(id)
                                 on delete cascade
                                 on update cascade
);

create table settings (
                          user_id uuid not null,
                          setting json not null default '{}',

                          constraint fk_users_settings
                              foreign key (user_id)
                                  references users(id)
                                  on delete cascade
                                  on update cascade
);

create table convers_IA (
                            id uuid primary key default uuid_generate_v4(),
                            user_id uuid not null,
                            nombre text not null,
                            created_at timestamp default current_timestamp,

                            constraint fk_users_conversia
                                foreign key (user_id)
                                    references users(id)
                                    on delete cascade
                                    on update cascade
);

create table messages_IA (
                             id uuid primary key default uuid_generate_v4(),
                             conversia_id uuid not null,
                             message text not null,
                             created_at timestamp default current_timestamp,
                             is_user boolean not null,
                             image varchar(255),

                             constraint fk_conversia_messages
                                 foreign key (conversia_id)
                                     references convers_IA(id)
                                     on delete cascade
                                     on update cascade
);

create table product (
                         id uuid primary key default uuid_generate_v4(),
                         profile_id uuid not null,
                         name varchar(255) not null,
                         description text not null,
                         points int not null,
                         created_at timestamp default current_timestamp,
                         updated_at timestamp default current_timestamp,
                         is_active boolean,

                         constraint fk_users_product
                             foreign key (profile_id)
                                 references profile(id)
                                 on delete cascade
                                 on update cascade
);

create table save_product (
                              user_id uuid not null,
                              product_id uuid not null,

                              constraint fk_users_saveProduct
                                  foreign key (user_id)
                                      references users(id)
                                      on delete cascade
                                      on update cascade,

                              constraint fk_product_saveProduct
                                  foreign key (product_id)
                                      references product(id)
                                      on delete cascade
                                      on update cascade
);

create table product_image (
                               product_id uuid not null,
                               image varchar(255) not null,

                               constraint fk_product_productImage
                                   foreign key (product_id)
                                       references product(id)
                                       on delete cascade
                                       on update cascade
);

create table sell_product (
                              product_id uuid not null primary key,
                              user_id uuid not null,
                              created_at timestamp default current_timestamp,

                              constraint fk_users_sellProduct
                                  foreign key (user_id)
                                      references users(id)
                                      on delete cascade
                                      on update cascade,

                              constraint fk_product_sellProduct
                                  foreign key (product_id)
                                      references product(id)
                                      on delete cascade
                                      on update cascade
);

create table product_category (
                                  product_id uuid not null,
                                  category varchar(100) not null,

                                  constraint fk_product_productCategory
                                      foreign key (product_id)
                                          references product(id)
                                          on delete cascade
                                          on update cascade
);

create table report_product (
                                id uuid primary key default uuid_generate_v4(),
                                product_id uuid not null,
                                user_id uuid not null,
                                type smallint not null,
                                description text not null,
                                created_at timestamp default current_timestamp,

                                constraint fk_product_reportProduct
                                    foreign key (product_id)
                                        references product(id)
                                        on delete cascade
                                        on update cascade,

                                constraint fk_users_reportProduct
                                    foreign key (user_id)
                                        references users(id)
                                        on delete cascade
                                        on update cascade
);

create table chat (
                      id_user1 uuid not null,
                      id_user2 uuid not null,
                      id_product uuid not null,
                      created_at timestamp default current_timestamp,

                      primary key (id_user1, id_user2, id_product),
                      constraint fk_users_chat1
                          foreign key (id_user1)
                              references users(id)
                              on delete cascade
                              on update cascade,

                      constraint fk_users_chat2
                          foreign key (id_user2)
                              references users(id)
                              on delete cascade
                              on update cascade,

                      constraint fk_product_chat
                          foreign key (id_product)
                              references product(id)
                              on delete cascade
                              on update cascade
);

create table notification (
                              id uuid primary key default uuid_generate_v4(),
                              user_emisor_id uuid not null,
                              user_receptor_id uuid not null,
                              type smallint not null,
                              content jsonb not null,
                              created_at timestamp default current_timestamp,
                              is_read boolean not null default false,

                              constraint fk_user_receptor_notification
                                  foreign key (user_emisor_id)
                                      references users(id)
                                      on delete cascade
                                      on update cascade,

                              constraint fk_user_emisor_notification
                                  foreign key (user_emisor_id)
                                      references users(id)
                                      on delete cascade
                                      on update cascade
);



