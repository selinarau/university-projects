import { browser } from "$app/environment";
import * as communitiesAPi from "$lib/apis/communitiesApi";

let communityState  = $state([]);

const initCommunities = async () => {
    if (browser) {
        communityState = await communitiesAPi.readCommunities();
    }
};

const initCommunity = async (id, loadedCommunity) => {
  if (browser) {
    const community = loadedCommunity ?? await communitiesAPi.readCommunity(id);
    if(community && !communityState.find((c)=> c.id === community.id)) {
      communityState.push(community);
    }
  }
};

const useCommunityState = () => {
    return {
        get communities() {
            return communityState;
        },
        addCommunity: async (community) => {
            const newCommunity = await communitiesAPi.createCommunity(community);
            communityState.push(newCommunity);
            return newCommunity;
        },
        removeCommunity: (id) => {
            communitiesAPi.deleteCommunity(id)
                .then(() => {
                communityState = communityState.filter((c) => c.id !== id);
                })
                .catch((error) => console.error(error));
        },
    };
};

export { initCommunity, initCommunities, useCommunityState };