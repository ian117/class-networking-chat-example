package network_class.chat_backend.database.entities;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {}

interface RoomRepository extends JpaRepository<Room, Long> {}
