package com.musicweb.music.dao;

import com.musicweb.music.entity.UserTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTbJpa extends JpaRepository<UserTb,Integer>{
}
