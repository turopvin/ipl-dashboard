import {React, useEffect} from "react";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";

export const TeamPage = () => {

    useEffect(
        () => {
            const fetchMatches = async () => {
                let url = 'http://localhost:8181/team/' + encodeURIComponent('Delhi Capitals')
                const response = await fetch(url)
                const data = await response.json()
                console.log(data)
            }
            fetchMatches()
        },
        []
    )
    return (
        <div className="TeamPage">
            <h1>Team name</h1>
            <MatchDetailCard/>
            <MatchSmallCard/>
            <MatchSmallCard/>
            <MatchSmallCard/>
        </div>
    );
}