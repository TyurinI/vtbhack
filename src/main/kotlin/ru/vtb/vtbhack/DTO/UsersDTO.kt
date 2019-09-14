package ru.vtb.vtbhack.DTO

import java.util.ArrayList



//data class UsersDTO(
//        val id: Long,
//        val human: String,
//        val rockGroups: RockGroups,
//        var songPlayersList: MutableList<SongPlayersDTO>?
//){
//    fun getPeopleDTOList(peopleList: List<People>): List<PeopleDTO> {
//        val peopleDTOList = ArrayList<PeopleDTO>()
//        for (people in peopleList) {
//            songPlayersList = ArrayList<SongPlayersDTO>()
//            val peopleDTO = PeopleDTO()
//            peopleDTO.setId(people.getId())
//            peopleDTO.setHuman(people.getHuman())
//            peopleDTO.setRockGroups(people.getRockGroups())
//            for (songPlayers in people.getSongItems()) {
//                val songPlayersDTO = SongPlayersDTO()
//                songPlayersDTO.setId(songPlayers.getId())
//                songPlayersDTO.setSong(songPlayers.getSong())
//                songPlayersDTO.setPoet(songPlayers.getPoet())
//                songPlayersDTO.setComposer(songPlayers.getComposer())
//                songPlayersDTO.setAlbum(songPlayers.getAlbum())
//                songPlayersList!!.add(songPlayersDTO)
//            }
//            peopleDTO.setSongPlayersList(songPlayersList)
//            peopleDTOList.add(peopleDTO)
//        }
//        return peopleDTOList
//    }